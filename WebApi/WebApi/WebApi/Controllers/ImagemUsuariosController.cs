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
    public class ImagemUsuariosController : ControllerBase
    {
        private readonly ocorrenciasdbContext _context;

        public ImagemUsuariosController(ocorrenciasdbContext context)
        {
            _context = context;
        }

        // GET: api/ImagemUsuarios
        [HttpGet]
        public IEnumerable<ImagemUsuario> GetImagemUsuario()
        {
            return _context.ImagemUsuario;
        }

        // GET: api/ImagemUsuarios/5
        [HttpGet("{id}")]
        public async Task<IActionResult> GetImagemUsuario([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var imagemUsuario = await _context.ImagemUsuario.FindAsync(id);

            if (imagemUsuario == null)
            {
                return NotFound();
            }

            return Ok(imagemUsuario);
        }

        // PUT: api/ImagemUsuarios/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutImagemUsuario([FromRoute] int id, [FromBody] ImagemUsuario imagemUsuario)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != imagemUsuario.CdImagem)
            {
                return BadRequest();
            }

            _context.Entry(imagemUsuario).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!ImagemUsuarioExists(id))
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

        // POST: api/ImagemUsuarios
        [HttpPost]
        public async Task<IActionResult> PostImagemUsuario([FromBody] ImagemUsuario imagemUsuario)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            _context.ImagemUsuario.Add(imagemUsuario);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetImagemUsuario", new { id = imagemUsuario.CdImagem }, imagemUsuario);
        }

        // DELETE: api/ImagemUsuarios/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteImagemUsuario([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var imagemUsuario = await _context.ImagemUsuario.FindAsync(id);
            if (imagemUsuario == null)
            {
                return NotFound();
            }

            _context.ImagemUsuario.Remove(imagemUsuario);
            await _context.SaveChangesAsync();

            return Ok(imagemUsuario);
        }

        private bool ImagemUsuarioExists(int id)
        {
            return _context.ImagemUsuario.Any(e => e.CdImagem == id);
        }
    }
}