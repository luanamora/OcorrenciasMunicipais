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
    public class TelefoneAreaatendimentoesController : ControllerBase
    {
        private readonly ocorrenciasdbContext _context;

        public TelefoneAreaatendimentoesController(ocorrenciasdbContext context)
        {
            _context = context;
        }

        // GET: api/TelefoneAreaatendimentoes
        [HttpGet]
        public IEnumerable<TelefoneAreaatendimento> GetTelefoneAreaatendimento()
        {
            return _context.TelefoneAreaatendimento;
        }

        // GET: api/TelefoneAreaatendimentoes/5
        [HttpGet("{id}")]
        public async Task<IActionResult> GetTelefoneAreaatendimento([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var telefoneAreaatendimento = await _context.TelefoneAreaatendimento.FindAsync(id);

            if (telefoneAreaatendimento == null)
            {
                return NotFound();
            }

            return Ok(telefoneAreaatendimento);
        }

        // PUT: api/TelefoneAreaatendimentoes/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutTelefoneAreaatendimento([FromRoute] int id, [FromBody] TelefoneAreaatendimento telefoneAreaatendimento)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != telefoneAreaatendimento.CdTelefoneareaatendimento)
            {
                return BadRequest();
            }

            _context.Entry(telefoneAreaatendimento).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!TelefoneAreaatendimentoExists(id))
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

        // POST: api/TelefoneAreaatendimentoes
        [HttpPost]
        public async Task<IActionResult> PostTelefoneAreaatendimento([FromBody] TelefoneAreaatendimento telefoneAreaatendimento)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            _context.TelefoneAreaatendimento.Add(telefoneAreaatendimento);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetTelefoneAreaatendimento", new { id = telefoneAreaatendimento.CdTelefoneareaatendimento }, telefoneAreaatendimento);
        }

        // DELETE: api/TelefoneAreaatendimentoes/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteTelefoneAreaatendimento([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var telefoneAreaatendimento = await _context.TelefoneAreaatendimento.FindAsync(id);
            if (telefoneAreaatendimento == null)
            {
                return NotFound();
            }

            _context.TelefoneAreaatendimento.Remove(telefoneAreaatendimento);
            await _context.SaveChangesAsync();

            return Ok(telefoneAreaatendimento);
        }

        private bool TelefoneAreaatendimentoExists(int id)
        {
            return _context.TelefoneAreaatendimento.Any(e => e.CdTelefoneareaatendimento == id);
        }
    }
}