using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using WebApi.Models;

namespace WebApi.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UsuarioAreaatendimentoesController : ControllerBase
    {
        private readonly ocorrenciasdbContext _context;

        public UsuarioAreaatendimentoesController(ocorrenciasdbContext context)
        {
            _context = context;
        }

        // GET: api/UsuarioAreaatendimentoes
        [HttpGet]
        public IEnumerable<UsuarioAreaatendimento> GetUsuarioAreaatendimento()
        {
            return _context.UsuarioAreaatendimento;
        }

        // GET: api/UsuarioAreaatendimentoes/5
        [HttpGet("{id}")]
        public async Task<IActionResult> GetUsuarioAreaatendimento([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var usuarioAreaatendimento = await _context.UsuarioAreaatendimento.FindAsync(id);

            if (usuarioAreaatendimento == null)
            {
                return NotFound();
            }

            return Ok(usuarioAreaatendimento);
        }

        // PUT: api/UsuarioAreaatendimentoes/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutUsuarioAreaatendimento([FromRoute] int id, [FromBody] UsuarioAreaatendimento usuarioAreaatendimento)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != usuarioAreaatendimento.CdUsuarioatendimento)
            {
                return BadRequest();
            }

            _context.Entry(usuarioAreaatendimento).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!UsuarioAreaatendimentoExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        // POST: api/UsuarioAreaatendimentoes
        [HttpPost]
        public async Task<IActionResult> PostUsuarioAreaatendimento([FromBody] UsuarioAreaatendimento usuarioAreaatendimento)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            _context.UsuarioAreaatendimento.Add(usuarioAreaatendimento);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetUsuarioAreaatendimento", new { id = usuarioAreaatendimento.CdUsuarioatendimento }, usuarioAreaatendimento);
        }

        // DELETE: api/UsuarioAreaatendimentoes/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteUsuarioAreaatendimento([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var usuarioAreaatendimento = await _context.UsuarioAreaatendimento.FindAsync(id);
            if (usuarioAreaatendimento == null)
            {
                return NotFound();
            }

            _context.UsuarioAreaatendimento.Remove(usuarioAreaatendimento);
            await _context.SaveChangesAsync();

            return Ok(usuarioAreaatendimento);
        }

        private bool UsuarioAreaatendimentoExists(int id)
        {
            return _context.UsuarioAreaatendimento.Any(e => e.CdUsuarioatendimento == id);
        }
    }
}